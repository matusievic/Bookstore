
# Results

## UseCases

Id  | Title | Scenario | Expected Result | Actual Result | Pass/Fail indication
 --|--|--|--|--|--
Functional requirements
#1U | View books in the catalog checking | Catalog link clicked | Books are displaying |  |
#2U | View book possibility checking | Catalog link clicked. After it, Learn more link clicked | Detailed book information is displaying  |  |
#3U | Sign up possibility checking | Sign up link clicked. Correct registration information was provided. Sign up button was clicked | Main page is displaying. Some additional menu items for a registered user is displaying. |  |
#4U | Sign up possibility checking | Sign up link clicked. Incorrect registration information was provided. Sign up button was clicked | Error message is displaying. |  |
#5U | Order book checking | Shopping cart was filled by clicking Add to Cart button in the catalog. Cart link clicked. Order link clicked. Shipping information was provided. OK button was clicked | A new order in Orders table (Account page) was added |  |
#6U | View account information checking | Account link was clicked | Account information is displaying |  |
#7U | Edit account information checking | Account link was clicked. Account information was changed. OK button was clicked | Account page is displaying. New information is displaying. |  |
#8U | Deactivate account checking | Account link was clicked. Deactivate button was clicked | Main page for unregistered user is displaying. There's no possibility to log in with deactivated account email |  |
#9U | Edit catalog categories checking | Catalog link was clicked. Edit categories button was clicked. Categories were edited | New categories information is displaying in the catalog
#10U | Add book checking | Catalog link was clicked. Add book button was clicked. Book information was provided. Save button was clicked. | New book information is displaying in the cataloged. |  |
#11U | Edit book checking | Catalog link was clicked. A book was selected. Edit book button was clicked. Book information was edited. Save button was clicked. | New book information is displaying in the cataloged. |  |
#12U | Remove book checking | Catalog link was clicked. A book was selected. Remove book button was clicked. | Book isn't displaying in the catalog. |  |
#13U | Remove user checking | Users link was clicked. A user was selected. Deactivate book button was clicked. | User isn't displaying in the table. |  |
#14U | Order acceptance checking | Orders link was clicked. An order was selected. Order id was clicked. Order information was edited. Ok button was clicked. | New order information is displaying in the table. |  |
Non functional requirements
#1NF | Security testing | Trying to CRUD books, categories, authors using non-administrator account | Cannot perform operations |  |
#2NF | Availability testing | Trying open web application in different browsers in different time | Web application is opening successfully |  |

# Conclusion
