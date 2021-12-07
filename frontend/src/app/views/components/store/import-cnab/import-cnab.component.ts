import {Component, ElementRef, Injectable, OnInit, ViewChild} from '@angular/core';
import {FileUploadService} from "../../../../services/file-upload.service";
import {Observable} from "rxjs";
import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
    selector: 'app-import-cnab',
    templateUrl: './import-cnab.component.html',
    styleUrls: ['./import-cnab.component.css']
})
export class ImportCnabComponent implements OnInit {

    selectedFiles?: FileList;
    currentFile?: File;
    progress = 0;
    message = '';

    @ViewChild('UploadFileInput') uploadFileInput: ElementRef;

    constructor(private uploadService: FileUploadService) {
    }

    ngOnInit(): void {}

    selectFile(event: any): void {
        this.selectedFiles = event.target.files;
    }

    upload(): void {

        if (this.selectedFiles) {
            const file: File | null = this.selectedFiles.item(0);

            if (file) {
                this.currentFile = file;
                this.uploadService.upload(this.currentFile).subscribe(
                    (event: any) => {
                        if (event.type === HttpEventType.UploadProgress) {
                            this.progress = Math.round(100 * event.loaded / event.total);
                        } else if (event instanceof HttpResponse) {
                            this.message = "Upload feito!"
                        }
                    },
                    (err: any) => {
                        console.log(err);
                        this.progress = 100;
                        if (err.error && err.error.message) {
                            this.message = 'Problema no upload!'
                            this.currentFile = undefined;
                        }

                    });
            }
            this.selectedFiles = undefined;
        }

    }

    resetMessage() {
        this.currentFile = undefined;
        this.message = '';
    }
}
