export class SEOData {
    id: number = 0;
    url: string = '';
    title: string = '';
    description: string = '';
    keywords: string[] = [];
    h1Count: number = 0;
    h2Count: number = 0;
    h3Count: number = 0;
    usesHTML5: boolean = false;
    imagesCount: number = 0;
    createdAt: string = '';


    constructor(id: number, url: string, title: string, description: string, keywords: string[], h1Count: number, h2Count: number, h3Count: number, usesHTML5: boolean, imagesCount: number, createdAt: string){
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.keywords = keywords;
        this.h1Count = h1Count;
        this.h2Count = h2Count;
        this.h3Count = h3Count;
        this.usesHTML5 = usesHTML5;
        this.imagesCount = imagesCount;
        this.createdAt = createdAt;
    }

  }