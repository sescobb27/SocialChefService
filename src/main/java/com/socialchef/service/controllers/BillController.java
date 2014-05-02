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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilePermission;

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

		chef_id = (listBills.get(iterator).getChef_id());

		for (Bill bill : listBills) {

			if (getChef_id() == bill.getChef_id()) {

				userListBill.add(bill);
				iterator++;

				continue;
			} else {
				usersListBills.add(userListBill);
				this.userListBill = new ArrayList<Bill>();
				userListBill.add(bill); // añado el nuevo
				chef_id = (listBills.get(iterator).getChef_id());
				iterator++;
				continue;

			}

		}
		for (int x = 0; x < usersListBills.size(); x++) {
			printer(usersListBills.get(x));

		}
		return usersListBills;

	}

	public void printer(List<Bill> userBill) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(userBill
					.get(0).getChef_id().toString()
					+ ".pdf"));
			document.open();
			Paragraph paragraph = new Paragraph();

			document.add(paragraph);

			PdfPTable table = new PdfPTable(6); // 6 columns.
			table.setSpacingBefore(25f);
			table.setSpacingAfter(25f);
			table = setColumnHeader(table);
			table = addRows(table, userBill);
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PdfPTable setColumnHeader(PdfPTable table) {

		PdfPCell cell1 = new PdfPCell(new Phrase("Nro de transaccion"));
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell2 = new PdfPCell(new Phrase("Fecha"));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell3 = new PdfPCell(new Phrase("Nombre del producto"));
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell4 = new PdfPCell(new Phrase("Cliente"));
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell5 = new PdfPCell(new Phrase("Costo total"));
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell5.setBackgroundColor(BaseColor.GRAY);
		PdfPCell cell6 = new PdfPCell(new Phrase("Comision"));
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell6.setBackgroundColor(BaseColor.GRAY);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);

		return table;

	}

	public PdfPTable addRows(PdfPTable table, List<Bill> userBill) {
		for (int x = 0; x < userBill.size(); x++) {

			PdfPCell cell1 = new PdfPCell(new Phrase(userBill.get(x)
					.getTransaction_id().toString()));
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Phrase(userBill.get(x).getDate()
					.toString()));
			table.addCell(cell2);
			PdfPCell cell3 = new PdfPCell(new Phrase(userBill.get(x)
					.getProductName()));
			table.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Phrase(userBill.get(x)
					.getCustomerName()));
			table.addCell(cell4);
			PdfPCell cell5 = new PdfPCell(new Phrase(userBill.get(x)
					.getTotalCost().toString()));
			table.addCell(cell5);
			PdfPCell cell6 = new PdfPCell(new Phrase(userBill.get(x)
					.getComission().toString()));
			table.addCell(cell6);
		}
		return table;

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
