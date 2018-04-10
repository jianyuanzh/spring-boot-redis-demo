package cc.databus.repository;

import cc.databus.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by vincent on 10/04/2018.
 */
@Repository
public class RedisRepositoryImp implements RedisRepository {

    private static final String KEY = "movie";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Object, Object> hashOperations;

    @Autowired
    public RedisRepositoryImp(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<Object, Object> findAllMovies() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void add(Movie movie) {
        hashOperations.put(KEY, movie.getId(), movie.getName());
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public Movie findMovie(String id) {
        return (Movie) hashOperations.get(KEY, id);
    }
}
