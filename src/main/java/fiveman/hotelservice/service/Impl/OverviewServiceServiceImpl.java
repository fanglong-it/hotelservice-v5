package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.OverviewService;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.OverviewServiceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OverviewServiceService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
public class OverviewServiceServiceImpl implements OverviewServiceService {

	Logger logger = LoggerFactory.getLogger(OverviewServiceServiceImpl.class);
	
	@Autowired
	OverviewServiceRepository overviewServiceRepository;

	@Override
	public List<OverviewService> getAllOverviewService() {
		logger.info("START GET ALL OVERVIEW");
		return overviewServiceRepository.findAll();
	}

	@Override
	public OverviewService getOverviewService(long id) {
		logger.info("START GET OVERVIEW BY ID");
		OverviewService overviewService = overviewServiceRepository.findOverviewServiceById(id);
		if (overviewService == null) {
			throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not Found Overview"));
		}
		
		logger.info("END GET OVERVIEW BY ID");
		return overviewService;
	}

	@Override
	public OverviewService addOverviewService(OverviewService newOverviewService) {
//		if(overviewServiceRepository.findOverviewServiceById(newOverviewService.getId()) != null){
//			throw new AppException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "The Id is Exist");
//		}else{
		logger.info("START ADD OVERVIEW");
		
		String description = newOverviewService.getDescription() == null ? Common.OVERVIEW_DESCRIPTION
				: newOverviewService.getDescription();
		String imageURL = newOverviewService.getImageUrl() == null ? Common.OVERVIEW_IMAGE_URL
				: newOverviewService.getImageUrl();
		String title = newOverviewService.getTitle() == null ? Common.OVERVIEW_TITLE : newOverviewService.getTitle();
		newOverviewService.setId(0);
		newOverviewService.setDescription(description);
		newOverviewService.setImageUrl(imageURL);
		newOverviewService.setTitle(title);
		overviewServiceRepository.save(newOverviewService);
		
		logger.info("END ADD OVERVIEW");
		return newOverviewService;

	}

	// This for check Overview fields is not null
	// Resist the duplicate code for checking
	OverviewService checkOverview(OverviewService newOverviewService) {
		logger.info("START CHECK OVERVIEW WHEN UPDATING");
		
		OverviewService oldOverviewService = overviewServiceRepository
				.findOverviewServiceById(newOverviewService.getId());
		String imageURL = Utilities.isEmptyString(newOverviewService.getImageUrl()) ? oldOverviewService.getImageUrl()
				: newOverviewService.getImageUrl();
		String title = Utilities.isEmptyString(newOverviewService.getTitle()) ? oldOverviewService.getTitle()
				: newOverviewService.getTitle();
		String description = Utilities.isEmptyString(newOverviewService.getDescription())
				? oldOverviewService.getDescription()
				: newOverviewService.getDescription();
		oldOverviewService.setDescription(description);
		oldOverviewService.setImageUrl(imageURL);
		oldOverviewService.setTitle(title);
		
		logger.info("END CHECK OVERVIEW WHEN UPDATING");
		return oldOverviewService;
	}

	@Override
	public OverviewService updateOverviewService(OverviewService newOverviewService) {
		logger.info("START UPPDATE OVERVIEW");
		OverviewService oldOverviewService = overviewServiceRepository
				.findOverviewServiceById(newOverviewService.getId());
		if (oldOverviewService == null) {
			throw new AppException(HttpStatus.NOT_FOUND.value(),
					new CustomResponseObject(Common.UPDATE_FAIL, "update overview is failed"));
		}
		oldOverviewService = checkOverview(newOverviewService);
		overviewServiceRepository.save(oldOverviewService);
		
		logger.info("END UPDATE OVERVIEW");
		return overviewServiceRepository.findOverviewServiceById(newOverviewService.getId());
	}

	@Override
	public String deleteOverviewService(long id) {
		logger.info("START DELETE OVERVIEW");
		OverviewService overviewService = overviewServiceRepository.findOverviewServiceById(id);
		if (overviewService == null) {
			throw new AppException(HttpStatus.NOT_FOUND.value(),
					new CustomResponseObject(Common.DELETE_FAIL, "delete overview is failed"));
		}
		overviewServiceRepository.deleteById(id);
		logger.info("END DELETE OVERVIEW");
		return "Delete successfully";
	}

}
