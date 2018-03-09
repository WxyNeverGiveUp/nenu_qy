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
 * date 15/5/13
 * time 下午2:51
 */
public class EmploymentStructureDTO {

    /**
     * 元组
     */
    public class Tuple{
        private String tupleName ;
        private int employmentCount;
        private double percentage;

        public String getTupleName() {
            return tupleName;
        }

        public void setTupleName(String tupleName) {
            this.tupleName = tupleName;
        }

        public int getEmploymentCount() {
            return employmentCount;
        }

        public void setEmploymentCount(int employmentCount) {
            this.employmentCount = employmentCount;
        }

        public double getPercentage() {
            return percentage;
        }

        public void setPercentage(double percentage) {
            this.percentage = percentage;
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
                if(row1.getTuples().get(sortColumn).getPercentage() > row2.getTuples().get(sortColumn).getPercentage())
                    return 1 ;
                if(row1.getTuples().get(sortColumn).getPercentage() < row2.getTuples().get(sortColumn).getPercentage())
                    return -1 ;
                else return 0 ;
            }
            else {
                if(row1.getTuples().get(sortColumn).getPercentage() > row2.getTuples().get(sortColumn).getPercentage())
                    return -1 ;
                if(row1.getTuples().get(sortColumn).getPercentage() < row2.getTuples().get(sortColumn).getPercentage())
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

    public EmploymentStructureDTO(){
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
     */
    public void setData( List<InformationStatisticDTO> employlist ){
        rowLine = new ArrayList<>();
        columnLine = new ArrayList<>();

        if( employlist.size() == 0 ) return;
        /**
         * 取得行列信息
         */
        for( InformationStatisticDTO informationStatisticDTO : employlist ) {
            boolean exist = false ;
            if( informationStatisticDTO.getDimension1() != null ) {
                for( String row : rowLine ) {
                    if( row.equals(informationStatisticDTO.getDimension1())) {
                        exist = true ;
                        break ;
                    }
                }
                if( exist == false ) rowLine.add( informationStatisticDTO.getDimension1() ) ;
            }
            else {
                for ( String row : rowLine ) {
                    if( row.equals("其他") ) {
                        exist = true;
                        break ;
                    }
                }
                if( exist == false ) rowLine.add("其他");
            }

            exist = false ;
            if( informationStatisticDTO.getDimension2() != null && !informationStatisticDTO.getDimension2().equals("") ) {
                for( String column : columnLine ) {
                    if( column.equals( informationStatisticDTO.getDimension2())) {
                        exist = true;
                        break;
                    }
                }
                if( exist == false ) columnLine.add(informationStatisticDTO.getDimension2());
            }
            else {
                for (String column : columnLine ) {
                    if( column.equals("其他")){
                        exist = true;
                        break;
                    }
                }
                if( exist == false ) columnLine.add("其他");
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
                tuple.setPercentage(0.0);
                row.getTuples().add(tuple);
            }

            rows.add(row);
        }

        /**
         * 往矩阵填入就业数据
         */
        for( InformationStatisticDTO informationStatisticDTO : employlist ) {
            Row findRow = null ;
            Tuple findColumn = null;
            for( Row row : rows ) {
                if( informationStatisticDTO.getDimension1() == null && row.getRowName().equals("其他")) {
                    findRow = row;
                    break;
                }
                if( row.getRowName().equals(informationStatisticDTO.getDimension1()) ) {
                    findRow = row;
                    break;
                }
            }

            for( Tuple tuple : findRow.getTuples() ) {
                if( informationStatisticDTO.getDimension2() == null && tuple.getTupleName().equals("其他") ) {
                    findColumn = tuple;
                    break;
                }
                if( tuple.getTupleName().equals(informationStatisticDTO.getDimension2()) ) {
                    findColumn = tuple;
                    break;
                }
            }
            findColumn.setEmploymentCount( informationStatisticDTO.getCount() );
        }

//        for( int i = 0 ; i < employlist.size() ; i++ ) {
//            int findRow = -1;
//            int findColumn = -1;
//            for( int j = 0 ; j < rows.size() ; j ++ ) {
//                if( employlist.get(i).getDimension1() == null && rows.get(j).getRowName().equals("") ) {
//                    findRow = j;
//                    break;
//                }
//                if( rows.get(j).getRowName().equals(employlist.get(i).getDimension1())){
//                    findRow = j;
//                    break;
//                }
//            }
//
//            for( int j = 0 ; j < rows.get(findRow).getTuples().size() ; j ++ ) {
//                if( employlist.get(i).getDimension2() == null && rows.get(findRow).getTuples().get(j).getTupleName().equals("")) {
//                    findColumn = j;
//                    break;
//                }
//
//                if( rows.get(findRow).getTuples().get(j).getTupleName().equals(employlist.get(i).getDimension2())){
//                    findColumn = j;
//                    break;
//                }
//            }
//            rows.get(findRow).getTuples().get(findColumn).setEmploymentCount(employlist.get(i).getCount());
//        }

        /**
         * 总计
         */
        if( rows.get(0).getTuples().size() >= 2 ) {
            for( Row row : rows ) {
                int employment = 0 ;
                for( Tuple tuple : row.getTuples() ){
                    employment += tuple.getEmploymentCount();
                }
                for( Tuple tuple : row.getTuples() ) {
                    tuple.setPercentage( (double)tuple.getEmploymentCount() / (double) employment);
                }
                Tuple tuple = new Tuple();
                tuple.setTupleName("总计");
                tuple.setEmploymentCount( employment );
                tuple.setPercentage((double) employment / (double)employment );

                row.getTuples().add(tuple);
            }


//            for (int i = 0; i < rows.size(); i++) {
//                int size = rows.get(i).getTuples().size();
//                int empolyment = 0 ;
//                for (int j = 0; j < rows.get(i).getTuples().size(); j++) {
//                    empolyment += rows.get(i).getTuples().get(j).getEmploymentCount();
//                }
//                for (int j = 0; j < rows.get(i).getTuples().size(); j++) {
//                    rows.get(i).getTuples().get(j).setPercentage((double)rows.get(i).getTuples().get(j).getEmploymentCount()/(double)graduation);
//                }
//
//                Tuple tuple = new Tuple();
//                tuple.setTupleName("总计");
//                tuple.setEmploymentCount( empolyment );
//                tuple.setPercentage((double) empolyment / (double)graduation );
//
//                rows.get(i).getTuples().add(tuple);
//
//            }
        }
        else {
            for( Row row : rows ) {
                row.getTuples().get(0).setTupleName("总计");
                row.getTuples().get(0).setPercentage(1.0);
            }

//            for(int i = 0; i < rows.size(); i++) {
//                rows.get(i).getTuples().get(0).setTupleName("总计");
//                rows.get(i).getTuples().get(0).setPercentage((double)rows.get(i).getTuples().get(0).getEmploymentCount()/(double)rows.get(i).getTuples().get(0).getGraduationCount());
//            }
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
     * 导出Excel
     * @return
     */
    public XSSFWorkbook getExcel() {
        XSSFWorkbook wb = new XSSFWorkbook() ;

        XSSFSheet sheet = wb.createSheet( "Sheet1" );

        XSSFRow row0 = sheet.createRow(0) ;

        XSSFCell cell00 = row0.createCell(0);
        cell00.setCellValue("");

        for( int i = 0 ; i < rows.get(0).getTuples().size() ; i ++ ){
            Tuple tuple = rows.get(0).getTuples().get(i);
            XSSFCell cell = row0.createCell( 2 * i + 1 );
            XSSFCell cell1 = row0.createCell( 2 * i + 2 ) ;
            cell.setCellValue( tuple.getTupleName());
            cell1.setCellValue("");

            sheet.addMergedRegion(new CellRangeAddress( 0 , 0 , 2 * i + 1 , 2 * i + 2) ) ;
        }

        XSSFRow row1 = sheet.createRow(1) ;

        XSSFCell cell10 = row1.createCell(0);
        cell10.setCellValue("");

        for( int i = 0 ; i < rows.get(0).getTuples().size() ; i ++ ) {
            XSSFCell cell = row1.createCell( 2 * i + 1 );
            XSSFCell cell1 = row1.createCell( 2 * i + 2 ) ;
            if( i < rows.get(0).getTuples().size() - 1 ) {
                cell.setCellValue("人数");
                cell1.setCellValue("比例");
            }
            else{
                cell.setCellValue("总数");
                cell1.setCellValue("比例");
            }
        }

        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00");
        for( int i = 0 ; i < rows.size() ; i ++ ) {
            XSSFRow row = sheet.createRow(2 + i) ;
            XSSFCell head = row.createCell(0);
            head.setCellValue( rows.get(i).getRowName());

            for( int j = 0 ; j < rows.get(i).getTuples().size() ; j ++ ) {
                XSSFCell cell = row.createCell( 2 * j + 1 ) ;
                XSSFCell cell1 = row.createCell( 2 * j + 2 ) ;
                cell.setCellValue(rows.get(i).getTuples().get(j).getEmploymentCount());
                cell1.setCellValue(df.format(rows.get(i).getTuples().get(j).getPercentage() * 100) + "%");
            }
        }

        return wb;
    }
}
