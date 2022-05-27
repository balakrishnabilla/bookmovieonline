package com.microservices.bookmovie.premiereservice.cache;

import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.entity.Show;
import com.microservices.bookmovie.premiereservice.repo.ShowRepository;
import com.microservices.bookmovie.premiereservice.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShowCache {
  @Autowired private ShowRepository showRepository;

  private final Map<Integer, List<ShowDTO>> showsByTheatorIdMap = new ConcurrentHashMap<>();

  public void load() {
    List<Show> list = showRepository.findAll();
    list.stream()
        .forEach(
            show -> {
              showsByTheatorIdMap.putIfAbsent(show.getTheatreId(), new ArrayList<>());
              showsByTheatorIdMap.get(show.getShowId()).add(ModelUtil.convertEntityToDTO(show));
            });
  }

  public Map<Integer, List<ShowDTO>> getMap() {
    return new HashMap<>(showsByTheatorIdMap);
  }

  public List<ShowDTO> get(int theatorId){
      return showsByTheatorIdMap.get(theatorId);
  }
}
