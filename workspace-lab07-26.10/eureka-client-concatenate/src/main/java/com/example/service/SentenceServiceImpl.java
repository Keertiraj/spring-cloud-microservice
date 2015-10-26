package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Build a sentence by assembling randomly generated subjects, verbs, 
 * articles, adjectives, and nouns.  The individual parts of speech will 
 * be obtained by calling the various DAOs.
 */
@Service
public class SentenceServiceImpl implements SentenceService {

	@Autowired WordService wordService;	

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		String sentence = "There was a problem assembling the sentence!";
			sentence = 
					wordService.getDemo().getString() + " "
					+ wordService.getDemo2().getString() + " "
					+ wordService.getDemo3().getString() + " "
					+ wordService.getDemo4().getString() + " "
					+ wordService.getDemo5().getString() + " "
			;
		return sentence;
	}	
}
