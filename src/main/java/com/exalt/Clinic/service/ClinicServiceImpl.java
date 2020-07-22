package com.exalt.Clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exalt.Clinic.exception.CommonException;
import com.exalt.Clinic.exception.ErrorEnum;
import com.exalt.Clinic.model.Clinic;
import com.exalt.Clinic.repository.ClinicRepository;

@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;

	@Transactional
	@Override
	public Clinic create(Clinic clinic) {

		return clinicRepository.save(clinic);
	}

	@Transactional(readOnly = true)
	@Override
	public Clinic get(String id) {

		if (!clinicRepository.existsById(id)) {
			throw new CommonException(ErrorEnum.WRONG_ID);
		}

		return clinicRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Clinic> getAll(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Clinic update(Clinic clinic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public String delete(String id) {
		if (!clinicRepository.existsById(id)) {
			throw new CommonException(ErrorEnum.WRONG_ID);

		} else {
			clinicRepository.deleteById(id);
		}
		if (clinicRepository.existsById(id)) {

			throw new CommonException(ErrorEnum.NOT_DELETED);

		}

		return "Deleted successsfuly";
	}

	@Transactional(readOnly = true)
	@Override
	public List<Clinic> gettNear(double longitude, double latitude, double distance) {

		return clinicRepository.findGeo(longitude, latitude, distance);
	}

}
