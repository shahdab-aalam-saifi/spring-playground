package com.saalamsaifi.spring.playground.service;

import java.io.File;

public interface IEncoderService {

  File encode(String fileName, String fileType, String fileBytes);
}
