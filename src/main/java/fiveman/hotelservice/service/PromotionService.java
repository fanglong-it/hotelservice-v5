package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Promotion;
import fiveman.hotelservice.response.CustomResponseObject;

public interface PromotionService {
	List<Promotion> getPromotions();
	Promotion getPromotion(long id);
	CustomResponseObject savePromotion(Promotion promotion);
	CustomResponseObject updatePromotion(Promotion promotion);
	CustomResponseObject deletePromotion(long id);
}
