package com.saalamsaifi.spring.playground.service.impl;

import com.saalamsaifi.spring.playground.common.utils.FileUtils;
import com.saalamsaifi.spring.playground.service.IEncoderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Qualifier("image")
public class ImageEncoderService implements IEncoderService {
  @Override
  public File encode(String fileName, String fileType, String fileBytes) {
    var decoder = Base64.getDecoder();
    var imageBytes = decoder.decode(fileBytes.getBytes(StandardCharsets.UTF_8));

    try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
      var image = ImageIO.read(bis);
      var file = new File(FileUtils.getUniqueFileName(fileName, fileType));

      ImageIO.write(image, fileType, file);

      return file;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
