package org.springframework.ai.mcp.sample.server;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Tool(description = "Send email to recipients")
    public String sendMail(List<String> recipients, String subject, String body) {
        // This is a stub implementation. In a real application, you would integrate with an email service.
        return String.format("Email sent to %s with subject '%s'", recipients.toString(), subject);
    }
}
