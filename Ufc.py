#'class' is used to create new data types

#a package has at least one class, most of packages have more than one, so we usually use ' from x import y' to import class y from package(file) x.

# an object is just a instance of a class

class UFCfighter:
    def __init__(self,name,reach,height,weight): #'__init__' means this function will automatically be called when you create a new instance for this class
        self.name = name #same as 'self.reach','self.height','self.weight', 'self.name' is an attribute of class 'UFCfighter'
        self.reach = reach
        self.height = height
        self.weight = weight

class FullName:
    def __init__(self,first_name,last_name):
        self.firstname = first_name
        self.lastname = last_name

    def name(self):
        return f'{self.firstname} {self.lastname}'




