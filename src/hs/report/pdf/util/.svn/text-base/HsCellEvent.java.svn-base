package hs.report.pdf.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPCellEvent;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.TextField;

public class HsCellEvent implements PdfPCellEvent
{
  /** the writer with the acroform */
	private PdfWriter writer;

	/** the current fieldname */
	private String fieldname = "NoName";

	/**
	 * Construct an implementation of PdfPCellEvent.
	 * 
	 * @param writer
	 *            the writer with the Acroform that will have to hold the
	 *            fields.
	 */
	public HsCellEvent(PdfWriter writer) {
		this.writer = writer;
	}

	/**
	 * Construct an implementation of PdfPCellEvent.
	 * 
	 * @param writer
	 *            the writer with the Acroform that will have to hold the
	 *            fields.
	 * @param fieldname
	 *            the name of the TextField
	 *  
	 */
	public HsCellEvent(PdfWriter writer, String fieldname) {
		this.writer = writer;
		this.fieldname = fieldname;
	}
  

	/**
	 * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell,
	 *      com.lowagie.text.Rectangle, com.lowagie.text.pdf.PdfContentByte[])
	 */
	public void cellLayout(PdfPCell cell, Rectangle position,
			PdfContentByte[] canvases) {
		TextField tf = new TextField(writer, position, fieldname);
		tf.setFontSize(10);
		try {
			PdfFormField field = tf.getTextField();
			writer.addAnnotation(field);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}