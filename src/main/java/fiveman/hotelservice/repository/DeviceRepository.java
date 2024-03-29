package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
	Device getDeviceById(long id);

	Device findTopByOrderByIdDesc();
}
