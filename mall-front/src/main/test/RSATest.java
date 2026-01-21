import com.qdbank.mall.util.RSAUtil;

public class RSATest {


    public static void main(String[] args) {
        //_________________________________________________


        String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW/V7SE4xt/NPecY4iuHVXC8RwslL3dtEXkZzB9OdCqiePYo/YS3J98bsBxRCespifqpBKFY4nyFytZnNUTl1pq5MFgxaQiLKtNjrg3zoHPISjYHwdeMj5evjtVrL5qcpU/rF9ZBdd0/E2P82tp37qo44xPs4m8OFIF0S66hb0BQIDAQAB";
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJb9XtITjG38095xjiK4dVcLxHCyUvd20ReRnMH050KqJ49ij9hLcn3xuwHFEJ6ymJ+qkEoVjifIXK1mc1ROXWmrkwWDFpCIsq02OuDfOgc8hKNgfB14yPl6+O1WsvmpylT+sX1kF13T8TY/za2nfuqjjjE+zibw4UgXRLrqFvQFAgMBAAECgYBGl06tiNYu1fV4Gj8JnJO/jpDY34ZKjBJVoo5XX9h/Im48ayg1R5DPsSRtP2T1zSnt/CBZgppjnxk1OKDS2ZByDFSVTcK8ubvUcCg1w5y9wX9yB6Igx2Dsj8dqMQZXbgtYZj8LTMSztOQzXfk0JJJhPjRMTEAX1w/KIeCebj8aAQJBAOn5+ke005ErQBtYrfDwbfMjpIlFclPHzPckIIMxhf8/DOc2Q6dKioY13hhjwt4c9dldWFe8rBLf5na8S/DP/IkCQQClM7MdxWB9I5TXiUBVd/mNT/0LLk9cES7IG/gRh4fQwPe8YdAqBJENGPxuKO4ls7h+nU8Si384Cv8XBMEyg3SdAkBzbSH+fAOfazHOC9qLsWDcgOnr2nnDQR8pkQYFEspjGGy6J7gKcKiT+0Ec0SJSRwE2AWnSpr5Q9WoRi2T/YOvZAkALgTI0HI6/qD6xU+mbCiPi53Mj2DHGo5uya+A2uE2JCCc4g0dP5cmEM/1AXrFXBtEOYD4leHl/maRyIe6iae0xAkAJGEMfUoGYkRIi3Z3ZIjdR88UiZj8BVVQ0brZOFvl/cfuiIfz2hF5369/Ur3fj09og3ARhw7/zxRC2STx8ppYq";
        //º”√‹

        String ss="{\"data\":{\"orderid\":\"QD202108041926040012\",\"timestamp\":\"1628076408978\",\"status\":\"S\",\"oriTransSer\":\"10193002\",\"oriTransDt\":\"20210804\",\"custId\":\"144\",\"acctType\":\"22\",\"queryType\":\"A\",\"transe\":\"5\",\"prodId\":\"156565194437439488\",\"transAmt\":\"0.00\"}}";
        String data = RSAUtil.encrypt(publicKey,privateKey,ss);
        System.out.println(data);

        //Ω‚√‹
        String s="g0VPadFBRRLsIzmm5LbHQGw5cqI6NbdSW9pOIQWPH63Sm+okPEzSBnKMCoEzbPCqPdbCA96+btdcg7WVC3UdfGMub9a/WLHDIzfi//8SruwtqjUearGoO8QkqBtR2LuI2MOrfM96koyRGgWuTcpZi5L7OOyL7v3jH0Gyp8rea8xj6w2qFdsxFbf65E7yWToG9ozKcWUwoLw1/xWG0TD3XdCXvpzOU3Dpscl6Tig2cIzIk8C0XqfxnCu38KP6jabzmU+IhVYF5WpqtFK4rhlndojWnAFhvd0/K6991cB+bTk0Yznt+fGM5z0ETpGw4lAb7R2h+lrKDhjXI+akbruPQ4vsd4vQ3Niu/0Mr8oVX8AaWe1N0LWUHPzZrW2DRS5d/dKfna5RnKC56HYFWJftnOvRjlXPqjkgYwNiSmQc9ODNJeWGEGSUyVtt6MghqdIDY/Rqd5zmULUU7ug1TJjub2B3kACkzkP+Ybs8ZHUKw9tmE441mWzW9/BXAvMCx6Gcd";
       s="AHbVJ3NZ/xU7Dvr/jh5NQt2hPdET9fHQLX2F56rsy24+tCdt3tvc8HpqgH53b3HxhuQnCVmRXqQFh8PuORhyFivhbOsR8tFY/LFhHyz/DGc3zXxK97tIzDSqD5Dd9kMxhcpKVVmoR7jYfHWOy1NgEwQRLGWbAPoQGMKKqp8irmcriBcF/8Ey6eeW4u93dH+tCapaz2xq72eml/0Cw8Aq03hrsI411sD1zaYVhnfyynFOmfvKPv75dFLFHVuEo+P5hxvANfnGq1snwtZyRX4qLPyVwfrQTwlpue9YXst+FwOnMzIVdOcE8qh4fRzWARCdw4SMo0QsZsQ5NTTEA7m5yzEJ2Eio3h1hkLNA7imBpj84Lg97h3GXA3U1YW7MLtHdxrOVGHgf1eVOeVV3Me2mg4J5PWDXavsRTQfXBACTpQWy9mdxvMGvxeGdh4k6Y7MRSlUzDBLZpXQsaqIjWDW1PwTUHJZGlQsiQkbVYf4/IvQfTp8PSDanvl/AzR6rNYRIJb85/IQqoA2c72fG/JSvfYy3COLlj5gs/gOl8WtG0pF38ZdCdceDL+UZU77138lBTfV4JzUkeSYmwQWFFUmxx9T68X5+52RoDoCB0p5dt/GBZtdaj+E51RF4RsMzlBGxI/MJD5X/sqGp8TkBTBPJlNsKbTZpcfQOqPvu3uwPiTk=";
        String  result11 =RSAUtil.decrypt(privateKey,publicKey,s);
        System.out.println(result11);
    }

}
