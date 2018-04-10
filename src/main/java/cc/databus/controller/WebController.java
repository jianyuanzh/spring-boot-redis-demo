package cc.databus.controller;

import cc.databus.model.Movie;
import cc.databus.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vincent on 10/04/2018.
 */
@RestController
@RequestMapping("/")
public class WebController {

    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping("/")
    public Map<Object, Object> index() {
        return redisRepository.findAllMovies();
    }

    @RequestMapping("/movies")
    public Map<String, String> findAll() {
        Map<Object, Object> all = redisRepository.findAllMovies();
        Map<String, String> allInString = new HashMap<>();
        for (Map.Entry<Object, Object> entry : all.entrySet()) {
            allInString.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return allInString;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam String key, @RequestParam String value) {
        Movie movie = new Movie(key, value);

        redisRepository.add(movie);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/movies/{key}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("key") String key) {
        redisRepository.delete(key);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
