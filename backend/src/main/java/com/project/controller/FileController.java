package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import com.project.dto.Will;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import org.springframework.stereotype.Controller;


import lombok.extern.slf4j.Slf4j;
//테스트용 파일 업로드 컨트롤러

@CrossOrigin(origins = { "*" },maxAge = 6000)

@RestController
public class FileController {
    

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") final MultipartFile multipartFile) {
		System.out.println("### upload");
		final File targetFile = new File("/" + multipartFile.getOriginalFilename());
		try {
			final InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
		} catch (final IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
		}
		return "redirect:/form";
	}



    // public ResponseEntity<Object> register(@Valid @RequestBody Will will){
    //     System.out.println(will);
    //     return new ResponseEntity<>("success", HttpStatus.OK);
    // }

    // @PostMapping("/will/read")
    // @ApiOperation(value = "유언장 읽기", notes = "유언장 읽기 기능입니다.")
    // @ApiImplicitParams({
    //         @ApiImplicitParam(name = "uid", value = "유언장 주인", required = true, dataType = "int"),
    //         @ApiImplicitParam(name = "receiver", value = "받을사람", required = true, dataType = "string"),
    // })
    // public ResponseEntity<Object> read(@Valid @RequestBody Will will){
    //     System.out.println(will);
    //     will.setContent("11");
    //     System.out.println(will.getContent());
    //     return new ResponseEntity<>("success", HttpStatus.OK);
    // }

}
