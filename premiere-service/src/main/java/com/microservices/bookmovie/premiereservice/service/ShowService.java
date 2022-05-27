package com.microservices.bookmovie.premiereservice.service;

import com.microservices.bookmovie.premiereservice.cache.ShowCache;
import com.microservices.bookmovie.premiereservice.dto.ShowDTO;
import com.microservices.bookmovie.premiereservice.dto.TheatreDTO;
import com.microservices.bookmovie.premiereservice.entity.Show;
import com.microservices.bookmovie.premiereservice.entity.Theatre;
import com.microservices.bookmovie.premiereservice.repo.ShowRepository;
import com.microservices.bookmovie.premiereservice.repo.TheatreRepository;
import com.microservices.bookmovie.premiereservice.util.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
  private TheatreRepository theatreRepo;
  private ShowRepository showRepo;
  @Autowired
  public ShowService(TheatreRepository theatreRepo, ShowRepository showRepo) {
    this.theatreRepo = theatreRepo;
    this.showRepo = showRepo;
  }

  public List<TheatreDTO> getShows(int cityId, int movieId, LocalDate localDate)
      throws ParseException {
    if (cityId == 0 || movieId == 0) {
      throw new ParseException(" Missing query parameters", 0);
    }
    List<Theatre> result = theatreRepo.findByCityIdAndMovieId(cityId, movieId);
    result.stream()
        .forEach(
            theatre ->
                theatre
                    .getShows()
                    .removeIf(
                        show -> show.getShowDate().getDayOfMonth() != localDate.getDayOfMonth()));

    List<TheatreDTO> collect =
        result.stream()
            .filter(theatre -> !theatre.getShows().isEmpty())
            .map(this::convertEntityToDTO)
            .collect(Collectors.toList());
    return collect;
  }

  private TheatreDTO convertEntityToDTO(Theatre theatre) {

    List<Show> shows = theatre.getShows();
    List<ShowDTO> showDTOList = ModelUtil.convertShowDTOs(shows);

    return new TheatreDTO(
        theatre.getTheatreId(),
        theatre.getTheatreName(),
        theatre.getCityId(),
        theatre.getMovieId(),
        showDTOList);
  }

  public List<ShowDTO> getShows(int theatreId) throws ParseException {

    if (theatreId == 0) {
      throw new ParseException(" Missing query parameters", 0);
    }

    List<Show> shows = showRepo.findByTheatreId(theatreId);

    return ModelUtil.convertShowDTOs(shows);
    // return showCache.getMap().get(theatreId);
  }

  public Long createShow(ShowDTO showDTO) {
    return new Long(showRepo.save(ModelUtil.convertDTOtoEntity(showDTO)).getShowId());
  }

  public void removeShow(long showId) {
    showRepo.deleteById(showId);
  }
}
