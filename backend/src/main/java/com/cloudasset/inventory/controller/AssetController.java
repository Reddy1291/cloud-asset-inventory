package com.cloudasset.inventory.controller;

import com.cloudasset.inventory.model.Asset;
import com.cloudasset.inventory.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin("*")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Asset> getAssets() throws Exception {
        return service.getAssets();
    }

    @GetMapping("/count")
    public int getCount() throws Exception {
        return service.getCount();
    }

    @GetMapping("/types")
    public Map<String, Long> getTypes() throws Exception {
        return service.getTypes();
    }

    @GetMapping("/search")
    public List<Asset> search(@RequestParam String name)
            throws Exception {
        return service.search(name);
    }
}
