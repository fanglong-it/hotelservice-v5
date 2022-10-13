package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Promotion;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.PromotionRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PromotionService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public List<Promotion> getPromotions() {
		log.info("GET ALL PROMOTIONS");
		return promotionRepository.findAll();
	}

	@Override
	public Promotion getPromotion(long id) {
		log.info("START GET PROMOTION BY ID");
		if (!promotionRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
        }
		log.info("END GET PROMOTION BY ID");
        return promotionRepository.getPromotionById(id);
	}

	@Override
	public CustomResponseObject savePromotion(Promotion promotion) {
		log.info("START SAVE PROMOTION");
		if (promotionRepository.existsById(promotion.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + promotion.getId()));
        }
		promotionRepository.save(promotion);
		log.info("END SAVE PROMOTION");
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding promotion Success!");
	}

	@Override
	public CustomResponseObject updatePromotion(Promotion promotion) {
		log.info("START UPDATE PROMOTION");
		if(promotionRepository.existsById(promotion.getId())){
			promotionRepository.save(promotion);
			log.info("END UPDATE PROMOTION");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + promotion.getId()));
	}

	@Override
	public CustomResponseObject deletePromotion(long id) {
		 if(promotionRepository.existsById(id)){
			 log.info("DELETE PROMOTION");
			 promotionRepository.deleteById(id);
	            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
	        }
		 throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
	}
}
