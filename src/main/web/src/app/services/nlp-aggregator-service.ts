import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject, Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable()
export class NlpAggregatorService {

    private Sentence_Parser_URL = environment.serverUrl + '/sentences';
    private Server_URL_Map = {
        'Sentence Detection' : environment.serverUrl + '/sentences',
        'Tokenization' : environment.serverUrl + '/tokens',
        'Name Entity Recognition' : environment.serverUrl + '/NER',
        'Finding Parts Of Speech' : environment.serverUrl + '/POSTags'
    }

    constructor(private http: HttpClient) {
    }

    public getParsedSentences(prop: string, textJson: any) {
        return this.http.post(this.Server_URL_Map[prop], textJson)
            .toPromise();
    }
}