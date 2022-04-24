package com.db.nace.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.db.nace.model.Nace;

@Configuration
@EnableBatchProcessing
public class NaceJobConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Nace> itemReader,
                   ItemProcessor<Nace, Nace> itemProcessor,
                   ItemWriter<Nace> itemWriter
    ) {

        Step step = stepBuilderFactory.get("NACE-file-load")
                .<Nace, Nace>chunk(100)
                .reader(itemReader(null))
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("Nace-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Nace> itemReader(@Value("#{jobParameters['fullFilePath']}") String filePath) {
//String filePath="";
    	System.out.print("File Path "+filePath);
        FlatFileItemReader<Nace> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource(filePath));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<Nace> lineMapper() {

        DefaultLineMapper<Nace> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
     //   lineTokenizer.setNames(new String[]{"Order","Level","Code","Parent","Description","This item includes",
       // 		"This item also includes","Rulings","This item excludes","Reference to ISIC Rev 4"});

        lineTokenizer.setNames(new String[]{"orderid","level","code","parent","discription","itemInclude","itemAlsoInclude","ruling","itemExcludes","refrenceToISIC"});
        BeanWrapperFieldSetMapper<Nace> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Nace.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}