package com.amine.gestiondestock.services.strategy;


import com.amine.gestiondestock.DTO.ArticleDTO;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.services.ArticleServ;
import com.amine.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDTO> {

  private FlickrService flickrService;
  private ArticleServ articleService;

  @Autowired
  public SaveArticlePhoto(FlickrService flickrService, ArticleServ articleService) {
    this.flickrService = flickrService;
    this.articleService = articleService;
  }

  @Override
  public ArticleDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    ArticleDTO article = articleService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    article.setPhoto(urlPhoto);
    return articleService.save(article);
  }
}
