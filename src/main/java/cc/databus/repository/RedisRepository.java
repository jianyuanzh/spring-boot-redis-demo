package cc.databus.repository;

import cc.databus.model.Movie;

import java.util.Map;

/**
 * Created by vincent on 10/04/2018.
 */
public interface RedisRepository {

    Map<Object, Object> findAllMovies();

    void add(Movie movie);

    void delete(String id);

    Movie findMovie(String id);
}
