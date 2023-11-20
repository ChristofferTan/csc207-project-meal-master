package data_access;

import api.edamam.GenerateRecipeAPICaller;
import entity.MealType;
import entity.Recipe;
import entity.RecipeFactory;
import use_case.generate_recipe.GenerateRecipeInputData;

import java.util.ArrayList;

public class FileDataAccessObjectTest {
    public Recipe generateRecipe() {
        GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData("chicken",
                new String[]{"balanced"},
                new String[]{"alcohol-free"},
                new String[]{"Asian"},
                new String[]{MealType.LUNCH.toString()},
                "0-1000",
                "0-100");
        return GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
    }

    @org.junit.Test
    public void testSaveRecipe() throws AssertionError {
        FileRecipeDataAccessObject frdao = new FileRecipeDataAccessObject(new RecipeFactory());
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("130 g brown rice");
        ingredients.add("4 skinless, boneless free-range chicken thighs");

        Recipe recipe1 = new RecipeFactory().create("Asian chicken rice balls & broth",
                "http://www.jamieoliver.com/recipes/chicken-recipes/asian-chicken-rice-balls-broth/", "https://edamam-product-images.s3.amazonaws.com/web-img/1b2/1b2ad43b57159a25a5187e26d4c5475a.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEAUaCXVzLWVhc3QtMSJHMEUCIQChv%2B3rUDYoT06FZpF73CDTGOEIXV54Rz%2B6LhKIoTVjFgIgC9Z2oUV3G2TLy5pbn4s1dKcp%2FcCR4coPiZ018xYJijQquAUIThAAGgwxODcwMTcxNTA5ODYiDI0TpWYYSK9PYG6UHSqVBZuAVLBGzapR5xe%2Fyz94nHcrKz7J3ECQJJHJNNa9KbRugz5wo66dMxRtCd5sZzEYU78b1xa98EShxXrPw2kamsLbMatMxxfi1cyLLDlCa95tCXNfpcpcvTeqEjgrEE9KDzG0NsyyXiyjgYHLcKKyUL94wb63z1goZv%2FMabivkMCjYFR%2BzHK0zcrAyllcx%2B9%2F1FE1V3UbPL8Zpezu1jNLbKGXeSzPA00gaDpEg9fIvHOtb4OwaKIigRKjFW3y4x%2FNk42HYjtcjwtJC%2BY78y3ecBBGQD6g1jNM8hbfT3BpuylRcKLfNhPggPbNJzNo%2B%2Bx85d0aJY%2FihiqsMz13LBAQMAR1Z1OPaicIgxtADjgyOhL4Foxpjq6Oc6GXuvxQOf%2FaprVBXzpLUfLB11CyrFcCiYUQLdoZuTLHvTkFBauZUgz2DCZkDsewxjtmib6lQLRblvjM3hJJPbsM5S%2BNP5pk7gu4GDnkOW2TvtvRVqXx%2F6vOBYVkbzbDOTAZVPa9tQc9r%2BRxOLr0HNye2fzZNBR33lUr2y1AmlHxhghcbaqweCS4BQfB%2BPAY8nDnMMUik5wjC73Ws%2FW24HDaKYxR48r8IewlqeNKpRSUmeD4H5FhVKKa8VarX%2BW9Ex3RLzefjAGq%2FHd3AzeCcvtNewstK%2FVqbh%2FK7alapZjviVds7UbRYehNrwEcNKJNzJg6VZdPfRJaLPqMQzj5o7duKI3CW0IU267CtP9qRzsfTyqrcCT2eHRB7H8khhPT8o1wciTDOwxMLStcpbAw%2Bh0y3hnlEH1o6bYOyQ6OlCPDdyTXhXus%2Fr4VPEcNx%2Bj%2FB3NDYf6rjolkRVbaXTPhqpSvw8kVXqVvgjJwObmCxk5oTqScFmSEPN6PvOKFGaUwgpnKqgY6sQFzT17FuqkHWDu4k2MxjObyggxu4LI%2FwYL%2BE8wGvXmABtFvr0n6i%2FIKyPYjqCjw%2BuYss5FtXPsy7ITjueBtBUUxKcPJDf%2FhzkQGKq1OUPPMpUClqaSELY0Snb4KPgptInXea%2BHa5V079giFhkJmQK1NQkJpfunY31K0V4PLpBwSRtvqN%2FkXjrYxOxDWi48eQGlizxlCd6ozQg3vJzpAWA2YyLcs8XKnV2eWoirgNjaYew0%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231113T213232Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFN3KM42WS%2F20231113%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=f1bc4af1301be34a8abd0bd4e7f0ecf4577db052924c1a72588177d3603f9287",
                112,
                ingredients,
                45,
                4);
        frdao.save(recipe1);
        Recipe savedRecipe1 = frdao.getRecipe("Asian chicken rice balls & broth");
        Recipe recipe2 = generateRecipe();
        frdao.save(recipe2);
        Recipe savedRecipe2 = frdao.getRecipe(recipe2.getLabel());

        assert savedRecipe1.equals(recipe1);
        assert savedRecipe2.equals(recipe2);
    }
}
