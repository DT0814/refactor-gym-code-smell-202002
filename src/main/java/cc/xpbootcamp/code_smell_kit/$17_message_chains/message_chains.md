```
 class Supermarket{
    val manager:Manager
    
    fun buyPork(money:Money):Pork{
        manager.buyPork(money);
    }
}
```

```
 class Manager{
    val seller:Seller
    
    fun buyPork(money:Money):Pork{
        seller.buyPork(SellerMoney(money));
    }
}
```
```
 class Seller{
    val cashier:Cashier
    
    fun buyPork(money:SellerMoney):Pork{
        cashier.buyPork(CashierMoney(money),demo:String);
    }
}
```
```
 class Cashier{
    
    fun buyPork(money:CashierMoney,demo:String):Pork{
        charge(money);
    }
}
```
