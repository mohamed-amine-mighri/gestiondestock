package com.amine.gestiondestock.services.strategy;


import com.amine.gestiondestock.DTO.FournisseurDTO;
import com.amine.gestiondestock.Exception.ErrorCodes;
import com.amine.gestiondestock.Exception.InvalidOperationException;
import com.amine.gestiondestock.services.FlickrService;
import com.amine.gestiondestock.services.FournisseurService;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDTO> {

  private FlickrService flickrService;
  private FournisseurService fournisseurService;

  @Autowired
  public SaveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
    this.flickrService = flickrService;
    this.fournisseurService = fournisseurService;
  }

  @Override
  public FournisseurDTO savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    FournisseurDTO fournisseur = fournisseurService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo du fournisseur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    fournisseur.setPhoto(urlPhoto);
    return fournisseurService.save(fournisseur);
  }
}
