package com.crawler.service.bo;

import java.util.List;

import com.crawler.service.model.Configuration;

public interface IConfigurationService {

	public List<Configuration> listActiveConfigurations();
	
}
