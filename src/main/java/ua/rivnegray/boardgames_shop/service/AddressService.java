//package ua.rivnegray.boardgames_shop.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ua.rivnegray.boardgames_shop.DTO.request.AddAndUpdateAddressDto;
//import ua.rivnegray.boardgames_shop.DTO.response.AddressDto;
//import ua.rivnegray.boardgames_shop.exceptions.AddressIdNotFoundException;
//import ua.rivnegray.boardgames_shop.model.Address;
//import ua.rivnegray.boardgames_shop.repository.AddressRepository;
//
//@Service
//public class AddressService {
//    private final AddressRepository addressRepository;
//
//    @Autowired
//    public AddressService(AddressRepository addressRepository) {
//        this.addressRepository = addressRepository;
//    }
//
//    public Address findAddressById(Long id){
//        return addressRepository.findById(id)
//                .orElseThrow(() -> new AddressIdNotFoundException(id));
//    }
//
//
//
//}
