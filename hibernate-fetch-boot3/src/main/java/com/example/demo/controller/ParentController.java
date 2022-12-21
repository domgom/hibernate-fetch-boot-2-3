package com.example.demo.controller;

import com.example.demo.model.Parent;
import com.example.demo.model.ParentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/parent")
public class ParentController {
    private final ParentRepository parentRepository;

    @GetMapping
    //@Transactional
    public ResponseEntity<String> getParent() throws InterruptedException {

        List<Parent> found = parentRepository.findJpql();
        log.info(">JPQL");
        sleep(100);
        log.info("Found in JPQL query {}", Arrays.toString(found.toArray()));

        List<Parent> foundNative = parentRepository.findNative();
        log.info(">Native");
        sleep(100);
        log.info("Found in Native query {}", Arrays.toString(foundNative.toArray()));

        return ResponseEntity.ok("Cool");
    }
}
