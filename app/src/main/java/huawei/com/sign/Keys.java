package huawei.com.sign;

/**
 * 作者：杨松松
 * 日期：2017/8/21 21:27
 * 邮箱：1026334022@qq.com
 */

public final class Keys {
    // 合作身份者id，以2088开头的16位纯数字
    public static final String DEFAULT_PARTNER = "2088102172268733";

    // 收款支付宝账号
    public static final String DEFAULT_SELLER = "ncubcj9338@sandbox.com";

    // 商户私钥，自助生成
    public static final String PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWYkTVbRPkc9kAblEmdr65qDkMj/7MI3/52CWffhiB+/eOGZ7BWN/ij/mMUknaF1+ZNqCBptckNasijg7hp5CP6cYWVG7TyDZmuiUObx93e4yz0LN2vPj8HfnqfpRBDl+3AVINSP/bluoPh6EJWe8ok889/7DeTf1oyIbixk7rMLqS+LSKN7zK2bELoUgHQYX8gNHPQL/0UfYJYrrlLSUl9FzplcIf6dGaYk6c4Prm2zDCv4QvvL1mQoJlfVTvnBVQ5Fhzp76OOwECnkwbgP39diGncr2oNGlso5au1JtXa8lyLtYeSM1umLWFUJI5INl9KFMCj3FsUn/v+aklWxSZAgMBAAECggEABDCkvlWVsk3QBAUO2cWBNucK/+CbNtDeZfWxxqnawPRmR11LYRO62TMtvs0kJaF1Fm3BNxuQ7v9Xz1Sp8XubuYbH8CNWGyx+54nUMnzCIbAdkX0FIw/M2Cl1pyTzuIRQAmdVA+2+wz2HM/uRbxNEaJsFsTKImQkPQHYl2/euhzr/PlM9NFC7Lu8hWu0EhL/2qvmlpjCk5DHLSl5Ige6Im7cSGfBhpWu4X2md3zEIKLgtTACRuuzuVBErdiScNFI3VBmPEUtyZjmujvrdD0DC8esroswPWuMKqgu8eQ5cB8SQ/lQbJ9f/JCpkX0akxEg/dOWY0csjvGNvr7REt58GIQKBgQDpeQwURdTKM2TfHMedqwGruLzLubu17Tbu3s0eWiOxZk+0sDCRvKLUiPuEg45AKr3kVmBPr72DYxExT6boZJomEsyoLGvN2hYX0v7GVc6gLDsMjj0GxJUvN3S3OtGaJmwRvuH+PQimAM3Ztj6V/bCA0qFdgvmcqNHYnT2wbrtjBQKBgQCk5Ny1o7rIBMQhjp9r3XGQY6+/cpHJI5wU4gWlfE95mh2IZekIUbOHtE/lvqEM7NsfiP2qViAjKR3BVuEHwDk4qVvw7f8jOHqaBaUf+QizPgHxxfmwTq1PXYD0pu1DEPNGA+ZKkXUCrKqJsys2/DU4QqIybe0ZhsXmiEvBNfWHhQKBgAoFbRRNrwWC0TiWZqcI2QiC5G+mKD110h7Mp2c/HTsXFiK+UUdgHmka/BjDAM8WRpJPdtDUzUCfdvRz48kj0E4EDo7FYVcznjElmFPxCO5tF5fBXzM0Kucc7UCUIL0KGydj1xVvjzh43l0S6nrLqdDNualm3snvoRo4HZa+GgL1AoGAFPva6nKPjmys2OhheUSQ8yL8doyJ6OLTNgUdWtXa90qZf4YYhaqAAkD7+eysagyqRfUU8txK8q6EztnTgT0BCyjiPmLLGRxS3c7iGJOEe+rkLHRZNgqNG5B2nKuxfdtmWf6dC9g+37xBZ4yZb0HhM+um4GwExD7WaGPn4AqR2FECgYEAmBFq4qi6WCtp5ONeQ4CRa7hzpV/rtz+p4S8VKtXCREQ+xCrhG+xv7FxYvC/OPbXNbG6qlvaNCI5QLUTIfEeoWwzXsk8USAsNb3gou2a04GT8//ta052yI4ZeWkIagspazqiEU+tuiwR9naheiLJK/J73/VLghAgwDcnlF+S+Pgc=";
    public static final String PUBLIC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApTPc259vVXtOsdG6ChzLF8TUkGVYlE8DgYPb4x6lIR9wRbOINdHe9XLiNbPJFRMvlqhTVm0HmwsaWDtySvLuwdLKpLmu83GbZ1z+jTzZGB1lMwTl3wpjjWze4wgzEBaDNpTU5v7fuvsdMy6VQX6SNMferlgr0rbV3j3buDjk1qxRTmSadZPM4RANCE9LxYkRNuHX8MJleHxMgZyny/bwXj5yyQATqThhyJTLkJj0DjXoZQtHb+Ax/vQ4lKhQ/yO5Oge51qB9j8lE/5ejJBngi9ZVqxVWlmcI15k3iQ3wYWeynKMSRBFxoEMgx9dhpaIPzqnhZ1ArW7NPGE7ECAcWFQIDAQAB";
    public static byte[] getPrivateKey() {
        return PRIVATE.getBytes();
    }
//
//    public static String getPrivateKey() {
//        return PRIVATE;
//    }
//
//    public static Strig getPublicKey() {
//        return PUBLIC;
//    }
}
