declare -a entities=("Adress" "Category" "Client" "Command" "CommandLine" "CommandLineId" "Product" "Supplier")
for e in "${entities[@]}"
   do

   # ${e,,} = lower case
   echo "$e ${e,,}"

   cp repositories/TemplateRepository.java "repositories/${e}Repository.java"
   cp services/TemplateService.java "services/${e}Service.java"
   cp exceptions/TemplateException.java "exceptions/${e}Exception.java"

   sed -i "s/Template/${e}/g" "repositories/${e}Repository.java"
   sed -i "s/template/${e,,}/g" "repositories/${e}Repository.java"
   
   sed -i "s/Template/${e}/g" "services/${e}Service.java"
   sed -i "s/template/${e,,}/g" "services/${e}Service.java"

   sed -i "s/Template/${e}/g" "exceptions/${e}Exception.java"
   sed -i "s/template/${e,,}/g" "exceptions/${e}Exception.java"

done
