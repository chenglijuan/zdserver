package com.lemi.msloan.util;
import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {
 /**
 * 
 */
 private static final long serialVersionUID = 4542617637761955078L;
 /**
 * currentPage 当前页
 */
 private int pageNum = 1;
 /**
 * pageSize 每页大小
 */
 private int pageSize = 10;
 /**
 * pageTotal 总页数
 */
 private int pageTotal;
 /**
 * recordTotal 总条数
 */
 private int recordTotal = 0;
 /**
 * previousPage 前一页
 */
 private int previousPage;
 /**
 * nextPage 下一页
 */
 private int nextPage;
 /**
 * firstPage 第一页
 */
 private int firstPage = 1;
 /**
 * lastPage 最后一页
 */
 private int lastPage;


 private int value;
 /**
 * content 每页的内容
 */
 private List<T> content;

 public void setPageNum(int pageNum) {
 this.pageNum = pageNum;
 }

 public void setPageSize(int pageSize) {
 this.pageSize = pageSize;
 }
 
 public void setRecordTotal(int recordTotal) {
 this.recordTotal = recordTotal;
 otherAttr();
 }

 public void setContent(List<T> content) {
 this.content = content;
 }
 
 public void otherAttr() {
 // 总页数
 this.pageTotal = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1 : this.recordTotal / this.pageSize;
 // 第一页
 this.firstPage = 1;
 // 最后一页
 this.lastPage = this.pageTotal;
 // 前一页
 if (this.pageNum > 1) {
 this.previousPage = this.pageNum - 1;
 } else {
 this.previousPage = this.firstPage;
 }
 // 下一页
 if (this.pageNum < this.lastPage) {
 this.nextPage = this.pageNum + 1;
 } else {
 this.nextPage = this.lastPage;
 }
 }
 // 放开私有属性
 public int getPageNum() {
 return pageNum;
 }
 public int getPageSize() {
 return pageSize;
 }
 public int getPageTotal() {
 return pageTotal;
 }
 public int getRecordTotal() {
 return recordTotal;
 }
 public int getPreviousPage() {
 return previousPage;
 }
 public int getNextPage() {
 return nextPage;
 }
 public int getFirstPage() {
 return firstPage;
 }
 public int getLastPage() {
 return lastPage;
 }
 public List<T> getContent() {
 return content;
 }

 public int getValue() {
  return value;
 }

 public void setValue(int value) {
  this.value = value;
 }
}