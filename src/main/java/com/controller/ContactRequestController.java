package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.ContactRequest;
import com.entities.RequestType;
import com.service.ContactRequestService;

@Controller
@RequestMapping("/api/contact")
public class ContactRequestController {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactRequestController.class);
    
    @Autowired
    private ContactRequestService contactRequestService;
    
    /**
     * API endpoint để tạo yêu cầu liên hệ từ form
     */
    @PostMapping("/submit")
    @ResponseBody
    @Transactional  // Add this annotation
    public ResponseEntity<Map<String, Object>> submitContactRequest(
            @RequestParam("fullName") String fullName,
            @RequestParam("phone") String phone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "projectId", required = false) Integer projectId,
            @RequestParam(value = "requestType", required = false) String requestTypeStr) {
    	System.out.println("=== CONTACT REQUEST RECEIVED ===");
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            logger.info("Received contact request: name={}, phone={}, projectId={}", 
                       fullName, phone, projectId);
            
            // Validate required fields
            if (fullName == null || fullName.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Họ tên là bắt buộc");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (phone == null || phone.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Số điện thoại là bắt buộc");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Validate phone format
            String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)]", "");
            if (!cleanPhone.matches("^[0-9]{10,11}$")) {
                response.put("success", false);
                response.put("message", "Số điện thoại không hợp lệ");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Parse request type
            RequestType requestType = RequestType.CONSULTATION; // default
            if (requestTypeStr != null && !requestTypeStr.trim().isEmpty()) {
                try {
                    requestType = RequestType.valueOf(requestTypeStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    logger.warn("Invalid request type: {}", requestTypeStr);
                }
            }
            
            // Create contact request
            ContactRequest contactRequest = contactRequestService.createFromForm(
                fullName.trim(),
                cleanPhone,
                email != null ? email.trim() : null,
                message != null ? message.trim() : null,
                projectId,
                requestType
            );
            
            response.put("success", true);
            response.put("message", "Yêu cầu của bạn đã được gửi thành công! Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất.");
            response.put("contactId", contactRequest.getId());
            
            logger.info("Contact request created successfully: ID={}, Name={}, Phone={}", 
                       contactRequest.getId(), fullName, phone);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error creating contact request", e);
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra khi gửi yêu cầu. Vui lòng thử lại sau.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * API endpoint đơn giản cho quick contact
     */
    @PostMapping("/quick")
    @ResponseBody
    @Transactional  // Add this annotation
    public ResponseEntity<Map<String, Object>> quickContact(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam(value = "projectId", required = false) Integer projectId) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            ContactRequest contactRequest = contactRequestService.createFromForm(
                name.trim(),
                phone.trim(),
                null, // no email
                "Yêu cầu tư vấn nhanh", // default message
                projectId,
                RequestType.CONSULTATION
            );
            
            response.put("success", true);
            response.put("message", "Cảm ơn bạn! Chúng tôi sẽ liên hệ với bạn trong 15 phút tới.");
            response.put("contactId", contactRequest.getId());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error creating quick contact", e);
            response.put("success", false);
            response.put("message", "Có lỗi xảy ra. Vui lòng thử lại sau.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}