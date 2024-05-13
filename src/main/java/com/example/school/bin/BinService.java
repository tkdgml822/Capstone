package com.example.school.service;

import com.example.school.bin.Bin;
import com.example.school.bin.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinService {

    private final BinRepository binRepository;

    @Autowired
    public BinService(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    public Bin saveBin(Bin bin) {
        return binRepository.save(bin);
    }
}