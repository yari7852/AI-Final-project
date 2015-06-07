class person:
    relationMatrix = []
    ID = -1
    def __init__(self,fileName,ID):
        self.relationMatrix = self.getRelationMatrix(fileName)
        self.ID = ID
        
    def getRelationMatrix(self,fileName):
        file = open(fileName,'r')
        list = []
        while True :
            Line = file.readline()
            if Line == '': break
            temp = Line.split('\n')
            temp = temp[0].split(' ')
            list.append(temp)
        
        return list
    
    def WhoYouDontLike(self,list):
        for ID in list:
            self.relationMatrix[self.ID][ID] = '-1'
            
PersonList = []
no1 = person('relationMatrix.txt',0)

no1.WhoYouDontLike([0,1,2])
PersonList.append(no1)
no2 = person('relationMatrix.txt',1)
no2.WhoYouDontLike([0])
PersonList.append(no2)
no3 = person('relationMatrix.txt',2)
no3.WhoYouDontLike([1])
PersonList.append(no3)
relationArray = []


howManyPeopleLikeHim = []
for p in PersonList:
    relationArray.append(p.relationMatrix[p.ID])
print relationArray
for ID in range(len(relationArray)):
    count = 0
    for otherID in range(len(relationArray)):
        if otherID == ID: continue
        if relationArray[otherID][ID] == '1': count = count + 1
    howManyPeopleLikeHim.append(count)
print howManyPeopleLikeHim



