package com.example.pbl4.section;


import com.example.pbl4.section.Section;
import com.example.pbl4.section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getSections() {
        return this.sectionRepository.findAll();
    }

    public Section findSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new IllegalStateException("Section not found"));
    }

    public void newSection(Section section) {
        Optional<Section> res = sectionRepository.findSectionByName(section.getName());
        if (res.isPresent() && section.getId() == null) {
            throw new IllegalStateException("Ya existe una seccion con ese nombre");
        }
        sectionRepository.save(section);
    }

    public void deleteSection(Long id) {
        boolean exists = sectionRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No existe un Section con esa id");
        }
        sectionRepository.deleteById(id);
    }
}
