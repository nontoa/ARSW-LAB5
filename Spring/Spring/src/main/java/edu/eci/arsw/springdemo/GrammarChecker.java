package edu.eci.arsw.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GrammarChecker {
	@Autowired
	@Qualifier("beanName1")
	public SpellChecker sc;
	@Autowired(required=false)
	public String x;
       
	public SpellChecker getSc() {
		return sc;
	}

	public void setSc(SpellChecker sc) {
		this.sc = sc;
	}

	public String getX() {
		return x;
	}
	@Autowired(required=false)
	public void setX(String x) {
		this.x = x;
	}
        
	public SpellChecker getSpellChecker() {
		return sc;
	}
	
	public void setSpellChecker(SpellChecker sc) {
		this.sc = sc;
	}


	public String check(String text){
		
		StringBuffer sb=new StringBuffer();
		sb.append("Spell checking output:"+sc.checkSpell(text));
		sb.append("Plagiarism checking output: Not available yet");
		
		
		return sb.toString();
		
	}
	
	
}
