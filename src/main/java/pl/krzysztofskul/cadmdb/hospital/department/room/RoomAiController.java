package pl.krzysztofskul.cadmdb.hospital.department.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.krzysztofskul.cadmdb.ai.AiService;

@RestController
@RequestMapping("/api/rooms")
public class RoomAiController {
  
  private final AiService ai;
  private final RoomRepo   roomRepo;

  public RoomAiController(AiService ai, RoomRepo roomRepo) {
    this.ai = ai;
    this.roomRepo = roomRepo;
  }

  @GetMapping("/{id}/describe")
  public String describeSection(
      @PathVariable Long id,
      @RequestParam String section
  ) {
    Room room = roomRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    String prompt = "";
    switch (section) {
	case "general": {
		// prepare a small prompt
	    prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the room should be designed and laid out in general.
		  Write your response in English and in Polish.
	      """,
	      room.getNameStandardized().getNameStandardizedEn(),
	      section
	    );
	    break;
	}
	case "floor": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the floor should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      room.getNameStandardized().getNameStandardizedEn(),
	      section
				  );		
		break;}
	case "ceiling": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the ceiling should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      room.getNameStandardized().getNameStandardizedEn(),
	      section
				  );
		break;}
	case "walls": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the walls should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      room.getNameStandardized().getNameStandardizedEn(),
	      section
				  );
		break;}
	case "lightning": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the lightning should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      room.getNameStandardized().getNameStandardizedEn(),
	      section
				  );
		break;}
	default:
		throw new IllegalArgumentException("Unexpected value: " + section);
	}
    

    return ai.chatCompletion(prompt);
  }

  @GetMapping("/standardized-name/{standardizedName}/describe")
  public String describeSectionStandardizedName(
      @PathVariable String standardizedName,
      @RequestParam String section
  ) {
    String prompt = "";
    switch (section) {
	case "general": {
		// prepare a small prompt
	    prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the room should be designed and laid out in general.
		  Write your response in English and in Polish.
	      """,
	      standardizedName,
	      section
	    );
	    break;
	}
	case "floor": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the floor should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      standardizedName,
	      section
				  );		
		break;}
	case "ceiling": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the ceiling should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      standardizedName,
	      section
				  );
		break;}
	case "walls": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the walls should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      standardizedName,
	      section
				  );
		break;}
	case "lightning": {
		  // prepare a small prompt
		  prompt = String.format("""
		  Given a room standardized name: %s.
		  Provide guidelines on how the lightning should be designed and finished.
		  Write your response in English and in Polish.
	      """,
	      standardizedName,
	      section
				  );
		break;}
	default:
		throw new IllegalArgumentException("Unexpected value: " + section);
	}
    

    return ai.chatCompletion(prompt);
  }

}
