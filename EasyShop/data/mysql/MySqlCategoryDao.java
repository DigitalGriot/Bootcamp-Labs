package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
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

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            ResultSet row = pstmt.executeQuery();

            while (row.next()) {
                Category category = mapRow(row);
                categories.add(category);
            }

            return categories;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }

        return null;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String sqlString = "SELECT * FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1, categoryId);
            ResultSet row = pstmt.executeQuery();

            if (row.next()) {
                return mapRow(row);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }

        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String sqlString = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1));
                    return category;
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }

        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String sqlString = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, categoryId);
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String sqlString = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1, categoryId);
            pstmt.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        // delete category by id and return status
        String sqlString = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }

        return false;
    }

    @Override
    public boolean updateCategory(int id, Category category) {
        // update category by id and return status
        String sqlString = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sqlString);
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getLocalizedMessage());
            System.out.println("In the MySqlCategoryDao");
        }

        return false;
    }

    @Override
    public void addCategory(Category category) {
        // add a new category
        create(category);
    }

    @Override
    public Category findById(int id) {
        return getById(id);
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);

        return category;
    }
}
