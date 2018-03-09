package com.pandawork.nenu.oa.common.dto.data;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * user: lishicao
 * date 15/5/11
 * time 下午3:20
 */
public class EmploymentRateDTO {
    /**
     * 元组
     */
    public class Tuple{
        private String tupleName ;
        private int graduationCount;
        private int employmentCount;
        private double employmentRate;

        public double getEmploymentRate() {
            return employmentRate;
        }

        public void setEmploymentRate(double employmentRate) {
            this.employmentRate = employmentRate;
        }

        public String getTupleName() {
            return tupleName;
        }

        public void setTupleName(String tupleName) {
            this.tupleName = tupleName;
        }

        public int getGraduationCount() {
            return graduationCount;
        }

        public void setGraduationCount(int graduationCount) {
            this.graduationCount = graduationCount;
        }

        public int getEmploymentCount() {
            return employmentCount;
        }

        public void setEmploymentCount(int employmentCount) {
            this.employmentCount = employmentCount;
        }
    }

    /**
     * 行
     */
    public class Row{
        private String rowName ;
        private List<Tuple> tuples;

        public String getRowName() {
            return rowName;
        }

        public void setRowName(String rowName) {
            this.rowName = rowName;
        }

        public List<Tuple> getTuples() {
            return tuples;
        }

        public void setTuples(List<Tuple> tuples) {
            this.tuples = tuples;
        }
    }

    /**
     * 排序的comparator
     */
    public class ComparatorRows implements Comparator {
        public int compare( Object arg0 , Object arg1 ) {
            Row row1 = (Row)arg0 ;
            Row row2 = (Row)arg1 ;

            if( !sortByDescend ) {
                if(row1.getTuples().get(sortColumn).getEmploymentRate() > row2.getTuples().get(sortColumn).getEmploymentRate())
                    return 1 ;
                if(row1.getTuples().get(sortColumn).getEmploymentRate() < row2.getTuples().get(sortColumn).getEmploymentRate())
                    return -1 ;
                else return 0 ;
            }
            else {
                if(row1.getTuples().get(sortColumn).getEmploymentRate() > row2.getTuples().get(sortColumn).getEmploymentRate())
                    return -1 ;
                if(row1.getTuples().get(sortColumn).getEmploymentRate() < row2.getTuples().get(sortColumn).getEmploymentRate())
                    return 1 ;
                else return 0 ;
            }
        }
    }

    private List<Row> rows ;

    private int sortColumn ;
    private boolean sortByDescend ;
    private List<String> rowLine;
    private List<String> columnLine;

    public EmploymentRateDTO(){
        rows = new ArrayList<>();
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    /**
     * 设置数据
     * @param employlist
     * @param studentlist
     */
    public void setData( List<InformationStatisticDTO> employlist , List<InformationStatisticDTO> studentlist ){
        rowLine = new ArrayList<>();
        columnLine = new ArrayList<>();
        if( studentlist.size() == 0 ) return;
        /**
         * 取得行列信息
         */
        for( int i = 0 ; i < studentlist.size() ; i ++ ) {
            boolean exist = false ;

            if( studentlist.get(i).getDimension1() != null ) {
                for (int j = 0; j < rowLine.size(); j++) {
                    if (rowLine.get(j).equals(studentlist.get(i).getDimension1())) {
                        exist = true;
                        break;
                    }
                }
                if (exist == false) {
                    rowLine.add(studentlist.get(i).getDimension1());
                }
            }
            else {
                for (int j = 0; j < rowLine.size(); j++) {
                    if (rowLine.get(j).equals("其他")) {
                        exist = true;
                        break;
                    }
                }
                if (exist == false) {
                    rowLine.add("其他");
                }
            }

            exist = false;

            if( studentlist.get(i).getDimension2() != null && !studentlist.get(i).getDimension2().equals("")) {
                for (int j = 0; j < columnLine.size(); j++) {
                    if (columnLine.get(j).equals(studentlist.get(i).getDimension2())) {
                        exist = true;
                        break;
                    }
                }

                if (exist == false) {
                    columnLine.add(studentlist.get(i).getDimension2());
                }
            }
            else {
                for (int j = 0; j < columnLine.size(); j++) {
                    if (columnLine.get(j).equals("其他")) {
                        exist = true;
                        break;
                    }
                }

                if (exist == false) {
                    columnLine.add("其他");
                }
            }
        }
        if( columnLine.size() == 0 ) columnLine.add("其他");

        /**
         * 按照行列信息新建矩阵
         */
        for( int i = 0 ; i < rowLine.size() ; i ++ ){
            Row row = new Row();
            row.setRowName(rowLine.get(i));
            row.setTuples(new ArrayList<Tuple>());
            for( int j = 0 ; j < columnLine.size() ; j ++ ) {
                Tuple tuple = new Tuple();
                tuple.setTupleName(columnLine.get(j));
                tuple.setEmploymentCount(0);
                tuple.setGraduationCount(0);
                tuple.setEmploymentRate(0.0);
                row.getTuples().add(tuple);
            }
            rows.add(row);
        }

        /**
         * 往矩阵填入生源数据
         */
        for( int i = 0 ; i < studentlist.size() ; i ++ ) {
            int findRow = -1 ;
            int findColumn = -1 ;
            for( int j = 0 ; j < rows.size() ; j ++ ) {
                if( studentlist.get(i).getDimension1() == null && rows.get(j).getRowName().equals("其他")){
                    findRow = j ;
                    break;
                }
                if( rows.get(j).getRowName().equals(studentlist.get(i).getDimension1())){
                    findRow = j ;
                    break;
                }
            }

            for( int j = 0 ; j < rows.get(findRow).getTuples().size() ; j ++ ) {
                if( studentlist.get(i).getDimension2() == null && rows.get(findRow).getTuples().get(j).getTupleName().equals("其他")) {
                    findColumn = j ;
                    break;
                }
                if( rows.get(findRow).getTuples().get(j).getTupleName().equals(studentlist.get(i).getDimension2())){
                //if( studentlist.get(i).getDimension2().equals(rows.get(findRow).getTuples().get(j).getTupleName())){
                    findColumn = j ;
                    break;
                }
            }
            rows.get(findRow).getTuples().get(findColumn).setGraduationCount(studentlist.get(i).getCount());
        }

        /**
         * 往矩阵填入就业数据
         */
        for( int i = 0 ; i < employlist.size() ; i++ ) {
            int findRow = -1;
            int findColumn = -1;
            for( int j = 0 ; j < rows.size() ; j ++ ) {
                if( employlist.get(i).getDimension1() == null && rows.get(j).getRowName().equals("其他")){
                    findRow = j;
                    break;
                }
                if( rows.get(j).getRowName().equals(employlist.get(i).getDimension1())){
                    findRow = j;
                    break;
                }
            }

            for( int j = 0 ; j < rows.get(findRow).getTuples().size() ; j ++ ) {
                if( employlist.get(i).getDimension2() == null && rows.get(findRow).getTuples().get(j).getTupleName().equals("其他")) {
                    findColumn = j;
                    break;
                }
                if( rows.get(findRow).getTuples().get(j).getTupleName().equals(employlist.get(i).getDimension2())){
                    findColumn = j;
                    break;
                }
            }
            rows.get(findRow).getTuples().get(findColumn).setEmploymentCount(employlist.get(i).getCount());
        }

        /**
         * 总计
         */
        if( rows.get(0).getTuples().size() >= 2 ) {
            for (int i = 0; i < rows.size(); i++) {
                int graduationSum = 0;
                int employSum = 0;
                for (int j = 0; j < rows.get(i).getTuples().size(); j++) {
                    graduationSum += rows.get(i).getTuples().get(j).getGraduationCount();
                    employSum += rows.get(i).getTuples().get(j).getEmploymentCount();
                }

                Tuple tuple = new Tuple();
                tuple.setTupleName("总计");
                tuple.setEmploymentCount(employSum);
                tuple.setGraduationCount(graduationSum);

                rows.get(i).getTuples().add(tuple);

                for (int j = 0; j < rows.get(i).getTuples().size(); j++) {
                    if (rows.get(i).getTuples().get(j).getGraduationCount() > 0) {
                        rows.get(i).getTuples().get(j).setEmploymentRate(
                                (double) rows.get(i).getTuples().get(j).getEmploymentCount() / (double) rows.get(i).getTuples().get(j).getGraduationCount());
                    } else {
                        rows.get(i).getTuples().get(j).setEmploymentRate(0);
                    }

                }
            }
        }
        else {
            for(int i=0; i<rows.size(); i++){
                rows.get(i).getTuples().get(0).setTupleName("总计");
                rows.get(i).getTuples().get(0).setEmploymentRate( (double)rows.get(i).getTuples().get(0).getEmploymentCount()/(double)rows.get(i).getTuples().get(0).getGraduationCount());
            }
        }
    }

    /**
     * 按照格式排序
     * @param column
     * @param descend
     */
    public void sort( int column , boolean descend ){
        sortColumn = column;
        sortByDescend = descend ;

        ComparatorRows comparatorRows = new ComparatorRows();
        Collections.sort(rows, comparatorRows);
    }

    /**
     * 返回EXCEL
     * @return
     */
    public XSSFWorkbook getExcel() {
        XSSFWorkbook wb = new XSSFWorkbook() ;

        XSSFSheet sheet = wb.createSheet("Sheet1");

        XSSFRow row0 = sheet.createRow(0) ;

        XSSFCell cell00 = row0.createCell(0);
        cell00.setCellValue("");

        for( int i = 0 ; i < rows.get(0).getTuples().size() ; i ++ ){
            Tuple tuple = rows.get(0).getTuples().get(i);
            XSSFCell cell = row0.createCell( 3 * i + 1 );
            XSSFCell cell1 = row0.createCell( 3 * i + 2 ) ;
            XSSFCell cell2 = row0.createCell( 3 * i + 3 ) ;
            cell.setCellValue( tuple.getTupleName());
            cell1.setCellValue("");
            cell2.setCellValue("");

            sheet.addMergedRegion(new CellRangeAddress( 0 , 0 , 3 * i + 1 , 3 * i + 3) ) ;
        }

        XSSFRow row1 = sheet.createRow(1) ;

        XSSFCell cell10 = row1.createCell(0);
        cell10.setCellValue("");

        for( int i = 0 ; i < rows.get(0).getTuples().size() ; i ++ ) {
            XSSFCell cell = row1.createCell( 3 * i + 1 );
            XSSFCell cell1 = row1.createCell( 3 * i + 2 ) ;
            XSSFCell cell2 = row1.createCell( 3 * i + 3 ) ;
            cell.setCellValue("就业人数");
            cell1.setCellValue("毕业人数");
            cell2.setCellValue("就业率");
        }

        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
        for( int i = 0 ; i < rows.size() ; i ++ ) {
            XSSFRow row = sheet.createRow(2 + i) ;
            XSSFCell head = row.createCell(0);
            head.setCellValue( rows.get(i).getRowName());

            for( int j = 0 ; j < rows.get(i).getTuples().size() ; j ++ ) {
                XSSFCell cell = row.createCell( 3 * j + 1 ) ;
                XSSFCell cell1 = row.createCell( 3 * j + 2 ) ;
                XSSFCell cell2 = row.createCell( 3 * j + 3 ) ;

                cell.setCellValue(rows.get(i).getTuples().get(j).getEmploymentCount());
                cell1.setCellValue(rows.get(i).getTuples().get(j).getGraduationCount());
                cell2.setCellValue(df.format(rows.get(i).getTuples().get(j).getEmploymentRate()*100) + "%");
            }
        }

        return wb;
    }
}
