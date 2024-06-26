package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }



    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String sqlString = "SELECT * FROM categories";

      try (Connection connection = getConnection()){

          PreparedStatement pstmt = connection.prepareStatement(sqlString);

          ResultSet row = pstmt.executeQuery(); //note we queried the DB to select all from the categories table
          // so the result set is really an array of categories

          while(row.next()){
            Category category = mapRow(row);
            categories.add(category);
          }

          return  categories;

    } catch (SQLException sqlException){
          System.out.println(sqlException.getLocalizedMessage());
          System.out.println("In the MYSqlCategory Dao");
    }

        return null;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String sqlString = "SELECT * FROM categories WHERE category_id = ?";

        try(Connection connection = getConnection()){

            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1,categoryId);
            ResultSet row = pstmt.executeQuery();

            while(row.next()){
                Category category = mapRow(row);
                return  category;
            }

        } catch(SQLException sqlException){
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySQL category doa");
        }


        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
