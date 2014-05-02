package com.socialchef.service.controllers;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Bill;
import com.socialchef.service.repositories.implementation.BillServiceRepository;

@Controller
public class BillController {
	@Autowired
	private BillServiceRepository BillRepo;
	private List<Bill> listBills;
	private List<Bill> userListBill = new ArrayList<Bill>();
	private List<List<Bill>> usersListBills = new ArrayList<List<Bill>>();
	private Integer chef_id = 0;

	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	@ResponseBody
	public List<List<Bill>> getAllProducts() {

		List<Bill> listBills = BillRepo.selectAll();

		int iterator = 0;

		chef_id= (listBills.get(iterator).getChef_id());

		for (Bill bill : listBills) {

			if (getChef_id() == bill.getChef_id()) {

				userListBill.add(bill);
				iterator++;
				
				continue;
			} else {
				usersListBills.add(userListBill);
				this.userListBill = new ArrayList<Bill>();
				userListBill.add(bill);
				chef_id= (listBills.get(iterator).getChef_id());
				continue;
				//iterator++;
			}

			

		}
		return usersListBills;

	}

	public Integer getChef_id() {
		return chef_id;
	}

	public void setChef_id(Integer chef_id) {
		this.chef_id = chef_id;
	}

	public List<Bill> getListBills() {
		return listBills;
	}

	public void setListBills(List<Bill> listBills) {
		this.listBills = listBills;
	}

	public List<Bill> getUserListBill() {
		return userListBill;
	}

	public void setUserListBill(List<Bill> userListBill) {
		this.userListBill = userListBill;
	}

}
