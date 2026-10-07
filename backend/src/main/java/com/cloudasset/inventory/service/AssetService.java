package com.cloudasset.inventory.service;

import com.cloudasset.inventory.model.Asset;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssetService {

    private List<Asset> loadAssets() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        InputStream input =
                getClass().getResourceAsStream("/data/assets.json");

        if (input == null) {
            throw new RuntimeException("assets.json not found");
        }

        return mapper.readValue(
                input,
                new TypeReference<List<Asset>>() {}
        );
    }

    public List<Asset> getAssets() throws Exception {
        return loadAssets();
    }

    public int getCount() throws Exception {
        return loadAssets().size();
    }

    public Map<String, Long> getTypes() throws Exception {
        return loadAssets()
                .stream()
                .collect(Collectors.groupingBy(
                        Asset::getAssetType,
                        Collectors.counting()
                ));
    }

    public List<Asset> search(String name) throws Exception {
        return loadAssets()
                .stream()
                .filter(asset ->
                        asset.getName() != null &&
                        asset.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase())
                )
                .toList();
    }
}
