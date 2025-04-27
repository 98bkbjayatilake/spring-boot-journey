package com.bimsara.Spring_6_rest_mvc.services;

import com.bimsara.Spring_6_rest_mvc.model.Beer;
import com.bimsara.Spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private Map<UUID,Beer> beerMap;

    public BeerServiceImpl(){
        this.beerMap=new HashMap<>();

        Beer beer1=Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("GalaxyCat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);
    }
@Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }
    @Override
   public Optional<Beer> getBeerById(UUID id){

     log.debug("Get Beer Id in service was called");

     return Optional.of(beerMap.get(id));
   }

    public Beer saveNewBeer(Beer beer){
     Beer savedBeer=Beer.builder()
             .id(UUID.randomUUID())
             .version(beer.getVersion())
             .beerName(beer.getBeerName())
             .beerStyle(beer.getBeerStyle())
             .upc(beer.getUpc())
             .quantityOnHand(beer.getQuantityOnHand())
             .price(beer.getPrice())
             .createdDate(LocalDateTime.now())
             .updateDate(LocalDateTime.now())
             .build();

     beerMap.put(beer.getId(),savedBeer);

     return savedBeer;
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer existing=beerMap.get(beerId);
        //set the properties of existing with passing the new properties of request body
        existing.setBeerName(beer.getBeerName());
        existing.setPrice(beer.getPrice());
        existing.setUpc(beer.getUpc());
        existing.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(existing.getId(),existing);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    public   void patchBeerById(UUID beerId, Beer beer){
        Beer existing=beerMap.get(beerId);

        //check if the new Beer name is not null or  empty, they update it
        if(StringUtils.hasText(beer.getBeerName())){
            existing.setBeerName(beer.getBeerName());
        }

        //check if the new beer style is not null,then update it
        if(beer.getBeerStyle()!=null){
            existing.setBeerStyle(beer.getBeerStyle());
        }

        //check if the new price is not null,then update it
        if(beer.getPrice()!=null){
            existing.setPrice(beer.getPrice());
        }

        // Check if the new quantity on hand is not null, then update it
        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        // Check if the new UPC is not null or empty, then update it
        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }
    }
}

