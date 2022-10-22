package com.news.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationDTO implements Serializable{
	public static final int size=1;
	private int totalPages;
	private int currentPage;
	private List<?> list;
	private boolean isFirst;
	private boolean isLast;
	
}
