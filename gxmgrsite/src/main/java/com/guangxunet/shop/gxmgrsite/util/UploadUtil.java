package com.guangxunet.shop.gxmgrsite.util;

import com.guangxunet.shop.base.util.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传工具
 * @author Administrator
 *
 */
public class UploadUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadUtil.class);

	public static String upload(MultipartFile file, String basePath) {
		String orgFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
		try {
			File sourceFile = new File(basePath, fileName);
			FileUtils.writeByteArrayToFile(sourceFile, file.getBytes());

			File targetFile = new File(Constants.PUBLIC_IMG_PATH, fileName);
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

			/*File targetFile = new File(Constants.PUBLIC_IMG_PATH, FilenameUtils.getName(sourceFile.getAbsolutePath()));
			FileUtils.copyFile(sourceFile, targetFile);*/

		} catch (IOException e) {
			LOGGER.error("图片上传异常:",e);
		}
		return fileName;
	}
}
