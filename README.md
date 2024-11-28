# dmaker
dmaker框架測試

## 資料夾架構
- common
  - 用於共用模組
- lib
  - 使用第三方的依賴jar檔
- q211
  - 表單名稱，後續可依據表單數量增加，如:q221，q222

## 表單
- 每個表單下有個src資料夾
- src下需有main、test兩個資料夾
- bin 資料夾，放service、model、parameter
  - service 每一個功能都有一個service 方便做單元測試
  - model分request與response，用於service傳遞資料
  - parameter放參數
- hproc相關繼承java如PLoad，主要接取輸入資料，並將輸出資料回傳。主要業務邏輯都在service
  - 好處是，之後轉換spring boot也可以快速使用此service 

## 共用程式
- dao 
  - 用於資料庫操作 
- exception
  - 用於異常處理 
- model
  - 用於資料模型 
- util
  - 用於公用方法- 
