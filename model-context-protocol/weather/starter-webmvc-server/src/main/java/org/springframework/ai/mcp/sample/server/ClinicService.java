package org.springframework.ai.mcp.sample.server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

	@Tool(description = "Find available doctors within a specified time range and their available time slots")
	public List<DoctorAvailability> findAvailableDoctors(String startTime, String endTime) {
		// Mock response with available doctors
		List<DoctorAvailability> availableDoctors = new ArrayList<>();

		availableDoctors.add(new DoctorAvailability(
			"Dr. Zhang",
			List.of(startTime, addHours(startTime, 1), addHours(startTime, 2))
		));

		availableDoctors.add(new DoctorAvailability(
			"Dr. Li",
			List.of(addHours(startTime, 1), addHours(startTime, 3))
		));

		return availableDoctors;
	}

	@Tool(description = "Create an appointment for a patient with a specific doctor at a given time")
	public AppointmentResult createAppointment(String doctorName, String appointmentTime) {
		// Mock appointment creation and return result
		return new AppointmentResult(
			"APT" + System.currentTimeMillis() % 100000,
			doctorName,
			appointmentTime,
			"Appointment confirmed"
		);
	}

	@Tool(description = "Get the current date and time")
	public String getCurrentTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	private String addHours(String timeStr, int hours) {
		try {
			LocalDateTime dateTime = LocalDateTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			return dateTime.plusHours(hours).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		} catch (Exception e) {
			return timeStr;
		}
	}

	public record DoctorAvailability(String doctorName, List<String> availableTimes) {}

	public record AppointmentResult(String appointmentId, String doctorName, String appointmentTime, String status) {}
}
