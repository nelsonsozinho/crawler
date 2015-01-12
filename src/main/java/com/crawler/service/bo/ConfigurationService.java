package com.crawler.service.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.service.dao.IGenericDAO;
import com.crawler.service.model.Configuration;

@Service(value = "configuration_service")
public class ConfigurationService implements IConfigurationService {

	@Autowired
	private IGenericDAO<Configuration> dao;
	
	@Override
	public List<Configuration> listActiveConfigurations() {
		String hql = "from Configuration as config where config.isActive = true";
		Map<String,Object> params = new HashMap<String, Object>();
		List<Configuration> configs = dao.listByParams(hql, params);
		return configs;
	}

}
