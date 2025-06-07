package com.antiTheftTracker.antiTheftTrackerApp.controller;
import com.antiTheftTracker.antiTheftTrackerApp.controllers.DeviceLocationController;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.DeviceLocationRequest;
import com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ.DeviceLocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DeviceLocationController.class)
public class DeviceLocationControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private DeviceLocationService locationService;

        @Test
        void shouldUpdateDeviceLocation() throws Exception {
            DeviceLocationRequest request = new DeviceLocationRequest("samsung123", 6.5244, 3.3792);
            ObjectMapper mapper = new ObjectMapper();

            mockMvc.perform(post("/api/v1/device/location")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(request)))
                    .andExpect(status().isAccepted());

            verify(locationService, times(1)).updateDeviceLocation(any(DeviceLocationRequest.class));
        }
    }


