package com.example.vfarmrdbackend.controller.material;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.vfarmrdbackend.model.material.MaterialStandardPercent;
import com.example.vfarmrdbackend.payload.material.MaterialStandardPercentRequest;
import com.example.vfarmrdbackend.payload.others.MessageResponse;
import com.example.vfarmrdbackend.service.material.MaterialStandardPercentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MaterialStandardPercent", description = "The MaterialStandardPercent's API")
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/api")
public class MaterialStandardPercentController {
    @Autowired
    MaterialStandardPercentService materialStandardPercentService;

    @GetMapping("/materialstandardpercents")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> getAllMaterialStandardPercent() {
        try {
            List<MaterialStandardPercent> listMaterialStandardPercents = materialStandardPercentService
                    .getAllMaterialStandardPercent();
            if (listMaterialStandardPercents != null) {
                return ResponseEntity.status(HttpStatus.OK).body(listMaterialStandardPercents);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new MessageResponse("L???i", "Kh??ng t??m th???y ti??u chu???n ph???n tr??m nguy??n li???u n??o!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }

    @GetMapping("/materialstandardpercents/{msp_id}")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> getMaterialStandardPercentById(@PathVariable("msp_id") int msp_id) {
        try {
            MaterialStandardPercent materialstandardpercents = materialStandardPercentService
                    .getMaterialStandardPercent(msp_id);
            if (materialstandardpercents != null) {
                return ResponseEntity.status(HttpStatus.OK).body(materialstandardpercents);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new MessageResponse("L???i", "Kh??ng t??m th???y ti??u chu???n ph???n tr??m nguy??n li???u n??o!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }

    @GetMapping("/materialstandardpercents/")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> getMaterialStandardPercentByMaterial_id(@RequestParam("material_id") String material_id) {
        try {
            MaterialStandardPercent materialstandardpercents = materialStandardPercentService
                    .getMaterialStandardPercentByMaterial_id(material_id);
            if (materialstandardpercents != null) {
                return ResponseEntity.status(HttpStatus.OK).body(materialstandardpercents);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new MessageResponse("L???i", "Kh??ng t??m th???y ti??u chu???n ph???n tr??m nguy??n li???u n??o!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }

    @PostMapping("/materialstandardpercents")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> createMaterialStandardPercent(@RequestBody MaterialStandardPercentRequest request,
            @RequestHeader("Authorization") String jwt) {
        try {
            if (materialStandardPercentService.createMaterialStandardPercent(request, jwt)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse("Th??nh c??ng", "T???o ti??u chu???n ph???n tr??m nguy??n li???u th??nh c??ng!"));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new MessageResponse("L???i", "Ti??u chu???n ph???n tr??m nguy??n li???u n??y ???? t???n t???i!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }

    @PutMapping("/materialstandardpercents/{msp_id}")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> updateMaterialStandardPercent(@PathVariable("msp_id") int msp_id,
            @RequestBody MaterialStandardPercentRequest request,
            @RequestHeader(required = false, value = "Authorization") String jwt) {
        try {
            if (materialStandardPercentService.updateMaterialStandardPercent(msp_id, request, jwt)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse("Th??nh c??ng", "C???p nh???t ti??u chu???n ph???n tr??m nguy??n li???u th??nh c??ng!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new MessageResponse("L???i", "Kh??ng t??m th???y ti??u chu???n ph???n tr??m nguy??n li???u n??o!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }

    @DeleteMapping("/materialstandardpercents/{msp_id}")
    @PreAuthorize("hasAuthority('manager') or hasAuthority('staff')")
    public ResponseEntity<?> deleteMaterialStandardPercent(@PathVariable("msp_id") int msp_id,
            @RequestHeader(required = false, value = "Authorization") String jwt) {
        try {

            if (materialStandardPercentService.deleteMaterialStandardPercent(msp_id, jwt)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse("Th??nh c??ng", "X??a ti??u chu???n ph???n tr??m nguy??n li???u th??nh c??ng!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new MessageResponse("L???i", "Kh??ng t??m th???y ti??u chu???n ph???n tr??m nguy??n li???u n??o!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new MessageResponse("L???i", "H??? th???ng ???? g???p s??? c???!"));
        }
    }
}
