package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class tryWithResourcesSample01 {

    public static void main(String[] args) {

        try {
            // ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Select文の実行と結果を格納/代入
            String sql = "SELECT + FROM country LIMIT 50";
            
            // try-with-resources構文
            try (// DBと接続する
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "dir-FY24matsuderawataru",
                        "wpjj7691W_M");
            // DBとやりとりする窓口（Statementオブジェクト）の作成
            Statement stmt = con.createStatement();
            // SQLを発行
            ResultSet rs = stmt.executeQuery(sql);) {


            // 結果を表示する
            while( rs.next() ) {
                // Name列の値を取得
                String name = rs.getString("Name");
                // 取得した値を表示
                System.out.println(name);
            }
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        }
    }
}
