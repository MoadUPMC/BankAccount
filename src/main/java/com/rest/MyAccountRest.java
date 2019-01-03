package com.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.businessbject.Account;
import com.businessbject.Operation;
import com.businessbject.TypeOperation;


@Controller
public class MyAccountRest {


	public MyAccountRest() {
		this.account =  new Account();
		List<Operation> operations = new ArrayList<Operation>();

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Operation op1 = new Operation(TypeOperation.Deposit, "Don", 100, now.format(formatter));

		operations.add(op1);
		this.account.setOperations(operations);

	}

	private static final String customerCookie = "bankcook";
	private Account account;

	@RequestMapping({"", "/"})
	public String index(Model model, HttpSession httpSession, HttpServletResponse response) {
		if (httpSession.getAttribute(customerCookie) == null){
			Cookie bankcook = new Cookie(customerCookie, randomAlphanumeric());
			httpSession.setAttribute(customerCookie, bankcook.getValue() );
			httpSession.setAttribute(bankcook.getValue(), this.account);
			response.addCookie(bankcook);
		}
		return "index";
	}

	@GetMapping("/deposit")
	public String deposit(HttpSession httpSession){
		if (httpSession.getAttribute(customerCookie) == null) {
			return "index";
		}
		return "deposit";
	}

	@PostMapping("/depositing")
	public String depositing(@CookieValue(name=customerCookie, required=true) String cookie, 
			HttpSession httpSession, Model model,
			@RequestParam(value="amount", required=true) Integer amount, 
			@RequestParam(value="nameOfOperation", required=true) String nameOfOperation){ // Depot
		if (httpSession.getAttribute(cookie) != null) {
			Operation operation = new Operation(TypeOperation.Deposit, nameOfOperation, amount, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			List<Operation> ops = this.account.getOperations();
			ops.add(operation);
			this.account.setOperations(ops);
			httpSession.setAttribute(cookie, this.account);
			model.addAttribute("account", this.account);
		}else {
			return "index";
		}
		return "history";
	}


	@GetMapping("/withdrawal")
	public String withdrawal(HttpSession httpSession){
		if (httpSession.getAttribute(customerCookie) == null) {
			return "index";
		}
		return "withdrawal";
	}

	@PostMapping("/withdrawaling")
	public String withdrawaling(@CookieValue(name=customerCookie, required=true) String cookie, 
			HttpSession httpSession, Model model,
			@RequestParam(value="amount", required=true) Integer amount, 
			@RequestParam(value="nameOfOperation", required=true) String nameOfOperation){ // Depot
		if (httpSession.getAttribute(cookie) != null) {
			Operation operation = new Operation(TypeOperation.Withdrawal, nameOfOperation, -1 * amount, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			List<Operation> ops = this.account.getOperations();
			ops.add(operation);
			this.account.setOperations(ops);
			httpSession.setAttribute(cookie, this.account);
			model.addAttribute("account", this.account);
		}else {
			return "index";
		}
		return "history";
	}

	@GetMapping("/history")
	public String accountStatement(@CookieValue(name=customerCookie, required=true) String cookie, HttpSession httpSession, Model model){ // Date Montant Solde
		if (httpSession.getAttribute(cookie) != null) {
			Account acc = (Account) httpSession.getAttribute(cookie);
			model.addAttribute("account", acc);
		}else {
			return "index";
		}
		return "history";
	}

	@GetMapping("/accountstatement")
	public String statementPrinting(@CookieValue(name=customerCookie, required=true) String cookie, HttpSession httpSession, Model model){
		if (httpSession.getAttribute(cookie) != null) {
			Account acc = (Account) httpSession.getAttribute(cookie);
			model.addAttribute("amount", acc.getActualAmount());
			model.addAttribute("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}else {
			return "index";
		}
		return "account-statement";
	}

	protected String randomAlphanumeric() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
